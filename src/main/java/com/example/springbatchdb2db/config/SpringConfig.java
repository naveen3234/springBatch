package com.example.springbatchdb2db.config;

import com.example.springbatchdb2db.model.Student;
import com.example.springbatchdb2db.model.StudentDTO;
import com.example.springbatchdb2db.model.StudentDTO2;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
@EnableBatchProcessing
public class SpringConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;


    @Autowired
    private DataSource dataSource;


    @Bean
    public JdbcCursorItemReader<Student>  reader()
    {
        JdbcCursorItemReader<Student> itemReader = new JdbcCursorItemReader<>();
        itemReader.setSql("select sid,marks,sage,sname from STUDENT_TBL");
        itemReader.setDataSource(dataSource);
        itemReader.setRowMapper(new StudentRowMapper());
        itemReader.setFetchSize(100);
        return  itemReader;
    }
    
    @Bean
    public JdbcCursorItemReader<StudentDTO>  reader2()
    {
        JdbcCursorItemReader<StudentDTO> itemReader = new JdbcCursorItemReader<>();
        itemReader.setSql("select sid,marks,sage,sname from StudentDTO");
        itemReader.setDataSource(dataSource);
        itemReader.setRowMapper(new StudentRowMapper2());
        itemReader.setFetchSize(100);
        return  itemReader;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public JdbcBatchItemWriter<StudentDTO> customerItemWriter() {
        JdbcBatchItemWriter<StudentDTO> itemWriter = new JdbcBatchItemWriter<>();

        itemWriter.setDataSource(dataSource);
        itemWriter.setSql("INSERT INTO StudentDTO VALUES (:sid, :sname, :sage, :marks)");
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider());
        itemWriter.afterPropertiesSet();

        return itemWriter;
    }
    
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public JdbcBatchItemWriter<StudentDTO2> customerItemWriter2() {
        JdbcBatchItemWriter<StudentDTO2> itemWriter = new JdbcBatchItemWriter<>();

        itemWriter.setDataSource(dataSource);
        itemWriter.setSql("INSERT INTO StudentDTO2 VALUES (:sid, :sname, :sage, :marks)");
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider());
        itemWriter.afterPropertiesSet();

        return itemWriter;
    }



  


    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Student,StudentDTO>chunk(1)
                .reader(reader())
                .processor(processor())
                .writer(customerItemWriter())
                .listener(new StepResultListener())
                .build();
    }
    
    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .<StudentDTO,StudentDTO2>chunk(1)
                .reader(reader2())
                .writer(customerItemWriter2())
                .listener(new StepResultListener())
                .build();
    }

    @Bean
    public Job job() {
        return jobBuilderFactory.get("job -" + ThreadLocalRandom.current().nextInt())
                .start(step1())
                .next(step2())
                .listener(new JobResultListener())
                .build();
    }

    @Bean
    public ItemProcessor<Student, StudentDTO> processor() {
        return new NaveenItemPocessor();
    }



}

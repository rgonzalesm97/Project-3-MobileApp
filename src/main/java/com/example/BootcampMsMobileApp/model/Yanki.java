package com.example.BootcampMsMobileApp.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("yanki")
public class Yanki implements Serializable{
    @Id
    String id;
    String documentNumber;
    String phoneNumber;
    String phoneIMEI;
    String email;


}

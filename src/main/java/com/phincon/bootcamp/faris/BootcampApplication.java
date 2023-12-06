package com.phincon.bootcamp.faris;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootcampApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootcampApplication.class, args);
    }

    // @Override
    // public void run(String... args) throws Exception {
    //     int limit = 10;
    //     for (int i = 1; i <= limit; i++) {
    //         AccountLatihan acc = new AccountLatihan();
    //         acc.setId(i);
    //         System.out.println("id : " + acc.getId());
    //     }
	// 	boolean max = true;
	// 	int d =0;
	// 	while (max) {
	// 		d++;
	// 		AccountLatihan acc = new AccountLatihan();
	// 		acc.setId(d);
	// 		System.out.println("id : " + acc.getId());

    //         if (d==5){
    //             max = false;
    //         }
	// 	}
    // }
}
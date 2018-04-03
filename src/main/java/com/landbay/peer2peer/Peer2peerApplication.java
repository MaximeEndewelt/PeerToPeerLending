package com.landbay.peer2peer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.landbay.peer2peer")
public class Peer2peerApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(Peer2peerApplication.class, args);
	}
}

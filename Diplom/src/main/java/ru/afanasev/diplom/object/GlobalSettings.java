package ru.afanasev.diplom.object;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "global_settings")
public class GlobalSettings {
	
	@Id
	private Integer id;
	
	private String code;
	
	private String name;
	
	private String value;
}

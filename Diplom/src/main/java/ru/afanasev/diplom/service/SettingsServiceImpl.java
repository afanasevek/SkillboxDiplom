package ru.afanasev.diplom.service;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import ru.afanasev.diplom.object.GlobalSettings;
import ru.afanasev.diplom.object.repository.SettingsRepository;

@Service
public class SettingsServiceImpl implements SettingsService {

	@Autowired
	SettingsRepository settingsRepository;

	@Override
	public List<GlobalSettings> getGlobalSettings() {

		return findAllSettings();
	}

	private List<GlobalSettings> findAllSettings() {

		return settingsRepository.findAll();
	}

}

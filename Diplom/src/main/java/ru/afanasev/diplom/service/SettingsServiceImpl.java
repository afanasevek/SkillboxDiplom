package ru.afanasev.diplom.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ru.afanasev.diplom.object.GlobalSettings;
import ru.afanasev.diplom.object.repository.SettingsRepository;

@Service
public class SettingsServiceImpl implements SettingsService {

	private final SettingsRepository settingsRepository;

	public SettingsServiceImpl(SettingsRepository settingsRepository) {
		super();
		this.settingsRepository = settingsRepository;
	}

	@Override
	public List<GlobalSettings> getGlobalSettings() {

		return findAllSettings();
	}

	private List<GlobalSettings> findAllSettings() {

		return settingsRepository.findAll();
	}

}

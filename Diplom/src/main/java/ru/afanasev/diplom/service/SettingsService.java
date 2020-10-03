package ru.afanasev.diplom.service;

import java.util.List;

import ru.afanasev.diplom.object.GlobalSettings;

public interface SettingsService {
	List<GlobalSettings> getGlobalSettings();
}

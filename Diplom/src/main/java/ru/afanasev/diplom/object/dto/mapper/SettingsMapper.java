package ru.afanasev.diplom.object.dto.mapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ru.afanasev.diplom.object.GlobalSettings;

public class SettingsMapper {

	public static Map<String, Boolean> entityToSettingsDtoResponse(List<GlobalSettings> listSettings) {

		Map<String, Boolean> settingMap = new LinkedHashMap<>();
		for (GlobalSettings settings : listSettings) {
			Boolean value;
			if (settings.getValue().equals("yes")) {
				value = true;
			} else {
				value = false;
			}
			settingMap.put(settings.getCode(), value);
		}

		return settingMap;

	}

}

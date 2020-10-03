package ru.afanasev.diplom.object.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.afanasev.diplom.object.GlobalSettings;

@Repository
public interface SettingsRepository extends JpaRepository<GlobalSettings, Integer> {

}

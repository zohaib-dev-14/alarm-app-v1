package com.zabisoft.alarm_app.repositories;

import com.zabisoft.alarm_app.entities.Alarm;
import com.zabisoft.alarm_app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AlarmRepository extends JpaRepository<Alarm, UUID> {
    List<Alarm> findByUser(User user);
    Optional<Alarm> findByIdAndUser(UUID id, User user);
}
package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.NotificationDTO;
import com.cosmin.licenta.workday.entity.Notification;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {EmployeeMapper.class})
public interface NotificationMapper {

    Notification domainToEntity(final NotificationDTO source);

    NotificationDTO entityToDomain(final Notification source);

    List<Notification> domainsToEntities(final List<NotificationDTO> sourceList);

    List<NotificationDTO> entitiesToDomains(final List<Notification> sourceList);

}

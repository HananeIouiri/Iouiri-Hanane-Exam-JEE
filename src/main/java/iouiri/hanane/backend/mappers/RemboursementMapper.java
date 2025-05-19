package iouiri.hanane.backend.mappers;

import iouiri.hanane.backend.dtos.RemboursementDTO;
import iouiri.hanane.backend.entities.Remboursement;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class RemboursementMapper {

    public RemboursementDTO toDTO(Remboursement remboursement) {
        RemboursementDTO dto = new RemboursementDTO();
        BeanUtils.copyProperties(remboursement, dto);
        return dto;
    }

    public Remboursement toEntity(RemboursementDTO dto) {
        Remboursement remboursement = new Remboursement();
        BeanUtils.copyProperties(dto, remboursement);
        return remboursement;
    }
}

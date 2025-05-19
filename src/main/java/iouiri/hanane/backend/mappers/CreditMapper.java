package iouiri.hanane.backend.mappers;

import iouiri.hanane.backend.dtos.CreditDTO;
import iouiri.hanane.backend.entities.Credit;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CreditMapper {

    public CreditDTO toDTO(Credit credit) {
        CreditDTO dto = new CreditDTO();
        BeanUtils.copyProperties(credit, dto);
        return dto;
    }

    public Credit toEntity(CreditDTO dto) {
        Credit credit = new Credit() {}; // anonyme pour instancier
        BeanUtils.copyProperties(dto, credit);
        return credit;
    }
}

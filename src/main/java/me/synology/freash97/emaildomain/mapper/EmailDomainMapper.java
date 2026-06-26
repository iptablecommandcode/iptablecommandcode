package me.synology.freash97.emaildomain.mapper;

import me.synology.freash97.emaildomain.domain.EmailDomainDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmailDomainMapper {
    List<EmailDomainDTO> findAllActive();
    List<EmailDomainDTO> findAll();
    void save(EmailDomainDTO emailDomainDTO);
    void updateUseYn(@Param("domainSq") int domainSq, @Param("useYn") String useYn);
    void delete(int domainSq);
}

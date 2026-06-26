package me.synology.freash97.emaildomain.service;

import me.synology.freash97.emaildomain.domain.EmailDomainDTO;

import java.util.List;

public interface EmailDomainService {
    List<EmailDomainDTO> findAllActive();
    List<EmailDomainDTO> findAll();
    void save(EmailDomainDTO emailDomainDTO);
    void updateUseYn(int domainSq, String useYn);
    void delete(int domainSq);
}

package me.synology.freash97.emaildomain.service.impl;

import me.synology.freash97.emaildomain.domain.EmailDomainDTO;
import me.synology.freash97.emaildomain.mapper.EmailDomainMapper;
import me.synology.freash97.emaildomain.service.EmailDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailDomainServiceImpl implements EmailDomainService {

    @Autowired
    private EmailDomainMapper emailDomainMapper;

    @Override
    public List<EmailDomainDTO> findAllActive() {
        return emailDomainMapper.findAllActive();
    }

    @Override
    public List<EmailDomainDTO> findAll() {
        return emailDomainMapper.findAll();
    }

    @Override
    public void save(EmailDomainDTO emailDomainDTO) {
        emailDomainMapper.save(emailDomainDTO);
    }

    @Override
    public void updateUseYn(int domainSq, String useYn) {
        emailDomainMapper.updateUseYn(domainSq, useYn);
    }

    @Override
    public void delete(int domainSq) {
        emailDomainMapper.delete(domainSq);
    }
}

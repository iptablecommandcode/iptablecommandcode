package me.synology.freash97.sign.mapper;

import me.synology.freash97.sign.domain.SignDTO;
import org.apache.ibatis.annotations.Mapper;

/**
 * packageName   : me.synology.freash97.sign.mapper
 * fileName      : SignMapper
 * author        : iptab
 * date          : 2025-04-28
 * time          : 오후 10:55
 * description   :
 * ====================================================
 * DATE                  AUTHOR              NOTE
 * ----------------------------------------------------
 * 2025-04-28               iptab             최초 생성
 */
@Mapper
public interface SignMapper {

    SignDTO findByUsername(String username);

    Integer save(SignDTO signDTO);

    SignDTO findByUserAccount(SignDTO signDTO);

}

package me.synology.freash97.sign.service.impl;

import me.synology.freash97.sign.domain.SignDTO;
import me.synology.freash97.sign.mapper.SignMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SignSerivceImplTest {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Mock
    private SignMapper signMapper;

    @Test
    void signUpEncodesPasswordBeforeSave() {
        SignSerivceImpl signService = new SignSerivceImpl(signMapper, passwordEncoder);
        SignDTO signDTO = new SignDTO();
        signDTO.setUsername("test-user");
        signDTO.setPassword("plain-password");

        signService.signUp(signDTO);

        ArgumentCaptor<SignDTO> captor = ArgumentCaptor.forClass(SignDTO.class);
        verify(signMapper).save(captor.capture());
        String savedPassword = captor.getValue().getPassword();
        assertThat(savedPassword).isNotEqualTo("plain-password");
        assertThat(passwordEncoder.matches("plain-password", savedPassword)).isTrue();
    }

    @Test
    void findByUserAccountMatchesEncodedPassword() {
        SignSerivceImpl signService = new SignSerivceImpl(signMapper, passwordEncoder);
        SignDTO request = new SignDTO();
        request.setUsername("test-user");
        request.setPassword("plain-password");
        SignDTO savedUser = user("test-user", passwordEncoder.encode("plain-password"));
        when(signMapper.findByUsername("test-user")).thenReturn(savedUser);

        SignDTO result = signService.findByUserAccount(request);

        assertThat(result).isNotNull();
        assertThat(result.getPassword()).isNull();
        verify(signMapper, never()).updatePassword(eq(1), anyString());
    }

    @Test
    void findByUserAccountUpdatesLegacyPlainPassword() {
        SignSerivceImpl signService = new SignSerivceImpl(signMapper, passwordEncoder);
        SignDTO request = new SignDTO();
        request.setUsername("test-user");
        request.setPassword("plain-password");
        when(signMapper.findByUsername("test-user")).thenReturn(user("test-user", "plain-password"));

        SignDTO result = signService.findByUserAccount(request);

        ArgumentCaptor<String> passwordCaptor = ArgumentCaptor.forClass(String.class);
        verify(signMapper).updatePassword(eq(1), passwordCaptor.capture());
        assertThat(passwordCaptor.getValue()).isNotEqualTo("plain-password");
        assertThat(passwordEncoder.matches("plain-password", passwordCaptor.getValue())).isTrue();
        assertThat(result).isNotNull();
        assertThat(result.getPassword()).isNull();
    }

    @Test
    void findByUserAccountReturnsNullWhenPasswordDoesNotMatch() {
        SignSerivceImpl signService = new SignSerivceImpl(signMapper, passwordEncoder);
        SignDTO request = new SignDTO();
        request.setUsername("test-user");
        request.setPassword("wrong-password");
        when(signMapper.findByUsername("test-user")).thenReturn(user("test-user", passwordEncoder.encode("plain-password")));

        SignDTO result = signService.findByUserAccount(request);

        assertThat(result).isNull();
        verify(signMapper, never()).updatePassword(eq(1), anyString());
    }

    private SignDTO user(String username, String password) {
        SignDTO user = new SignDTO();
        user.setUserId(1);
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }
}

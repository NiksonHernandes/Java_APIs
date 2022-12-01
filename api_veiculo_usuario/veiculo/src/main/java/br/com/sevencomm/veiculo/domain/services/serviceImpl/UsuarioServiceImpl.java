package br.com.sevencomm.veiculo.domain.services.serviceImpl;

import br.com.sevencomm.veiculo.data.repositories.RoleRepository;
import br.com.sevencomm.veiculo.data.repositories.UsuarioRespository;
import br.com.sevencomm.veiculo.domain.models.Usuario;
import br.com.sevencomm.veiculo.domain.models.dtos.SignUpDTO;
import br.com.sevencomm.veiculo.domain.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRespository _usuarioRespository;

    @Autowired
    private RoleRepository _roleRepository;

    @Override
    public List<Usuario> listAllUsuario() {
        return _usuarioRespository.findAll();
    }

    @Override
    @Transactional
    public Usuario signUp(SignUpDTO signUpDTO) {
        if(_usuarioRespository.existsByLogin(signUpDTO.getLogin())) {
            throw new IllegalArgumentException("Login já existente!");
        }

        if(signUpDTO.getNome().equals("") || signUpDTO.getSenha().equals("") || signUpDTO.getEmail().equals("")
        || signUpDTO.getLogin().equals("")) {
            throw new IllegalArgumentException("Campos vazios");
        }

        if(!Objects.equals(signUpDTO.getSenha(), signUpDTO.getSenhaConfirmacao())){
            throw new IllegalArgumentException("Senha diferente");
        }

        Usuario _usuario = new Usuario();
        _usuario.setNome(signUpDTO.getNome());
        _usuario.setLogin(signUpDTO.getLogin());
        _usuario.setEmail(signUpDTO.getEmail());

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String senhaCriptografada = bCryptPasswordEncoder.encode(signUpDTO.getSenha());
        _usuario.setSenha(senhaCriptografada);

        _usuario.setRoles(Arrays.asList(_roleRepository.findByNome("ROLE_USER")));

        _usuario = _usuarioRespository.save(_usuario);

        return _usuario;
    }

}

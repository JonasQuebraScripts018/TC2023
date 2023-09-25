package com.agendados.master.Service;

import com.agendados.master.Model.M_Empresa;
import com.agendados.master.Model.M_Resposta;
import com.agendados.master.Repository.R_Empresa;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class S_Empresa {
    private static R_Empresa r_empresa;

    public static M_Empresa getEmpresaLogin(String cnpj, String senha) {
        cnpj = NumberCleaner.cleanerNumber(cnpj);
        if(cnpj.equals("")){
            return r_empresa.findByUsuarioESenha(null,senha);
        }else{
            return r_empresa.findByUsuarioESenha(Long.valueOf(cnpj), senha);
        }
    }

    public static M_Resposta cadastrarEmpresa(String nome, String cnpj, String email, String telefone, String cep,String senha, String confsenha) {

        boolean cadastrovalido = true;
        String mensagemRetorno = "";
        if (!senha.equals(confsenha)) {
            mensagemRetorno += "A senha e a confirmação de senha devem ser iguais.<br/>";
            cadastrovalido = false;
        } if (!cnpj.equals(cnpj)) {
            mensagemRetorno += "cnpj inválido.<br/>";
            cadastrovalido = false;
        } if (nome == null || nome.trim() == "") {
            mensagemRetorno += "Deve ser informado um nome.<br/>";
            cadastrovalido = false;
        } if ((email == null || email.trim() == "")) {
            mensagemRetorno += "E-mail precisa ser informado.<br/>";
            cadastrovalido = false;
        } if(cadastrovalido) {
            M_Empresa m_empresa = new M_Empresa();
            m_empresa.setNome(nome);
            m_empresa.setCnpj(NumberCleaner.cleanerNumber(cnpj));
            m_empresa.setEmail(email);
            m_empresa.setTelefone(NumberCleaner.cleanerNumber(telefone));
            m_empresa.setCep(NumberCleaner.cleanerNumber(cep));
            m_empresa.setSenha(senha);
            try {
                r_empresa.save(m_empresa);
                mensagemRetorno += "Cadastro realizado com sucesso!";
            }
            catch (DataIntegrityViolationException e){
                if(e.getMessage().contains("u_key")){
                    mensagemRetorno +="O cnpj fornecido ja existe.";

                }
                else{
                    mensagemRetorno+="Erro ao cadastrar.";
                }
                cadastrovalido = false;
            }
        }
        M_Resposta m_resposta = new M_Resposta(cadastrovalido, mensagemRetorno);
        return m_resposta;
    }

}

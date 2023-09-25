package com.agendados.master.Repository;

import com.agendados.master.Model.M_Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface R_Empresa extends JpaRepository<M_Empresa, Long> {
        @Query(value = "SELECT * FROM Empresa WHERE cnpj = :cnpj and senha = :senha limit 1", nativeQuery = true)
        M_Empresa findByUsuarioESenha(@Param("cnpj") Long cnpj, @Param("senha") String senha);

}

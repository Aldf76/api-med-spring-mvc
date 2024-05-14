package med.voll.api.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.endereco.DadosEndereco;


public record DadosCadastroPaciente(
		@NotBlank
		String nome,
		@NotBlank
		@Email
		String email,
		@NotBlank
		String telefone,
		@NotBlank
		@Pattern(regexp = "\\d{11,11}")
		String cpf,
		@NotNull @Valid
		DadosEndereco endereco) {

}

// a classe record executa o mesmo papel da classe DTO nas versões
// mais antigas do projeto que fizemos neste mesmo módulo.

//o record faz a verificação para cadastrar esses valores listados
// certificando que esão cumprindo os requisitos setados e também se os valores estão sendo de fatos preenchidos

//Cada grupo de informações terá um record( médico, endereço, etc) 
//Também criaremos meios para que "puxemos" no get, cada grupo específico de informações separadamente
//Até porque nem sempre queremos ceder todos os dados de um objeto de uma vez, às vezes certos dados podem ser delicados
//como por exemplo documntos

//

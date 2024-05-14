package med.voll.api.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(
	@NotBlank
	String logradouro,
	@NotBlank
	String bairro,
	@NotBlank
	@Pattern(regexp = "\\d{8}")
	String cep,
	@NotBlank
	String cidade,
	@NotBlank
	String uf,
	String complemento,
	String numero
	) {

	public String logradouro() {
		return logradouro;
	}

	public String bairro() {
		return bairro;
	}

	public String cep() {
		return cep;
	}

	public String cidade() {
		return cidade;
	}

	public String uf() {
		return uf;
	}

	public String complemento() {
		return complemento;
	}

	public String numero() {
		return numero;
	}

	
	
}

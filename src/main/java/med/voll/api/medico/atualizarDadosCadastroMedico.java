package med.voll.api.medico;

import jakarta.annotation.Nonnull;
import med.voll.api.endereco.DadosEndereco;

public record atualizarDadosCadastroMedico(
	@Nonnull
	Long id,
	String nome,
	String telefone,
	DadosEndereco endereco
	)
{

	
	
	
}

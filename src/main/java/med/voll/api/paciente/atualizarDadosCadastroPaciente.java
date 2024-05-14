package med.voll.api.paciente;

import jakarta.annotation.Nonnull;
import med.voll.api.endereco.DadosEndereco;

public record atualizarDadosCadastroPaciente(
		
		@Nonnull
		Long id,
		String nome, 
		String telefone, 
		DadosEndereco endereco
		

		
		)
{

	
}

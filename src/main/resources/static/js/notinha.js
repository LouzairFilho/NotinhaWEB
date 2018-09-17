$(function(){

//	$('.js-busca-cliente').on('blur', function(event) {
//		buscacliente(event);		
//	});
	
	$('.js-busca-cliente-notinha').on('click', function(event) {
		event.preventDefault();
		buscacliente(event);		
	});
	
	
	
	
});

function buscacliente(event) {
	var inputIdCliente = $("#idCliente");
	var idCliente = $("#idCliente").val();
	var urlBusca = inputIdCliente.attr('href')+ idCliente;
	
	if(idCliente != '' || idCliente != null){
		var response = $.ajax({
			url: urlBusca,
			type: 'GET'
		});
		
		response.done(function(e) {
			
			$("#idCliente").val(e.id);
			$("#nomeRazao").val(e.nomeRazao);
			$("#nomeFantasia").val(e.nomeFantasia);
			
		});
		
		response.fail(function(e) {
			alert('Cliente não encotrado');
		});
	}
	
	
}
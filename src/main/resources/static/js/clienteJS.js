function trocaPfPj() {
				var i = document.cliente.tipoPessoa.selectedIndex;
				var tipoPessoa = document.cliente.tipoPessoa[i].text;
				var cnpj = "Jurídica";
				var cpf = "Física";
				var elCpfCnpj = document.getElementById('cpfCnpj');
				

				if (tipoPessoa == cnpj) {
					document.getElementById("lbCpfCnpj").innerHTML = "CNPJ";
					document.getElementById("lbnomeRazao").innerHTML = "Razão Social";
					document.getElementById("cpfCnpj").value="";
					elCpfCnpj.setAttribute("onkeypress","mascara(this,cnpj)");
					elCpfCnpj.setAttribute("maxLength","18");
					
				}
				if (tipoPessoa == cpf) {
					document.getElementById("lbCpfCnpj").innerHTML = "CPF";
					document.getElementById("lbnomeRazao").innerHTML = "Nome";
					document.getElementById("cpfCnpj").value="";
					elCpfCnpj.setAttribute("onkeypress","mascara(this,cpf)");
					elCpfCnpj.setAttribute("maxLength","14");
					
				}
			}
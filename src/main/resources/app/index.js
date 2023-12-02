const inicializarEstadoApp = () => {
  localStorage.setItem("estadoApp", JSON.stringify({
    usuarios: [
      { usuario: "John", senha: "123456" },
      { usuario: "Jane", senha: "asdf" },
    ],
  }));
};

inicializarEstadoApp();

const fazerLogin = () => {
  const usuarioInput = document.getElementById("usuario").value;
  const senhaInput = document.getElementById("senha").value;

  login(usuarioInput, senhaInput);
};

const login = (usuario, senha) => {
  const estadoApp = JSON.parse(localStorage.getItem("estadoApp"));

  const usuarioEncontrado = estadoApp.usuarios.find((u) =>
    u.usuario.toLowerCase() === usuario.toLowerCase()
    && u.senha.toLowerCase() === senha.toLowerCase()
  );

  if (usuarioEncontrado) {
    abrirModal("Usuário conectado com sucesso");
  } else {
    abrirModal("Usuário não encontrado");
  }
};

const abrirModal = (texto) => {
  document.getElementById('modal-text').innerText = texto;
  document.getElementById('modal').style.display = 'block';
  document.getElementById('modal-overlay').style.display = 'block';
}

const fecharModal = () => {
  document.getElementById('modal').style.display = 'none';
  document.getElementById('modal-overlay').style.display = 'none';
}

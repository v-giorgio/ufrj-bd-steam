/*
6. trazer dados do usuário, incluindo número de jogos obtidos e número de conquistas obtidas
> será útil para a tela de listagem de usuários e, também, para a tela de perfil de um usuário
*/
SELECT 
    U.id AS usuario_id,
    U.apelido,
    U.nome AS nome_usuario,
    COUNT(DISTINCT JU.fk_Jogo_id) AS jogos_obtidos,
    COUNT(DISTINCT UC.fk_Conquista_id) AS conquistas_obtidas
FROM Usuario U
LEFT JOIN JogoUsuario JU ON U.id = JU.fk_Usuario_id
LEFT JOIN UsuarioConquista UC ON U.id = UC.fk_Usuario_id
GROUP BY U.id, U.apelido, U.nome
ORDER BY jogos_obtidos DESC, conquistas_obtidas DESC;
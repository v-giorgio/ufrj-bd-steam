/*
1. trazer os jogos associados a um usuário, com o nome do jogo, imagem da capa, conquistas obtidas do total de conquistas e tempo jogado
> será útil para tela de exibir lista de jogos de um usuário
(i) mais de uma junção
(ii) agregação (count)
(iii) subconsultas aninhadas
*/
SELECT 
    J.nome AS nome_jogo,
    JU.tempo_jogado AS tempo_jogado,
    -- contar o total de conquistas do jogo
    (SELECT COUNT(C.id)
     FROM Conquista C
     WHERE C.fk_Jogo_id = J.id) AS total_conquistas,
    -- contar as conquistas obtidas pelo usuário
    (SELECT COUNT(UC.fk_Conquista_id)
     FROM UsuarioConquista UC
     JOIN Conquista C ON UC.fk_Conquista_id = C.id
     WHERE UC.fk_Usuario_id = JU.fk_Usuario_id
       AND C.fk_Jogo_id = J.id) AS conquistas_obtidas,
    -- obter a URL da imagem de capa
    (SELECT I.url
     FROM Imagem I
     WHERE I.fk_Jogo_id = J.id
       AND I.capa = TRUE
     LIMIT 1) AS imagem_capa
FROM 
    JogoUsuario JU
JOIN 
    Jogo J ON JU.fk_Jogo_id = J.id
WHERE 
    JU.fk_Usuario_id = '76561197996063158'
ORDER BY 
    total_conquistas DESC, conquistas_obtidas DESC;


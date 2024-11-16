/* 
8. trazer todas as mídias de um jogo (vídeo e imagem)
> será útil para tela de detalhes do jogo
*/
SELECT 
    'Imagem' AS tipo_midia,
    I.url AS url_midia,
    I.capa AS eh_capa
FROM Imagem I
WHERE I.fk_Jogo_id = '564310'

UNION ALL

SELECT 
    'Video' AS tipo_midia,
    V.url AS url_midia,
    NULL AS eh_capa
FROM Video V
WHERE V.fk_Jogo_id = '564310';
import pandas as pd

from utils import get_user_details, get_user_friends, pre_process_users_df

# 1) pegar dados do usuário default
USER_DEFAULT = "76561197960435530"
df_users = get_user_details(USER_DEFAULT)

# 2) pegar dados de 19 amigos do usuario e fazer pré-processamento
df_friends = get_user_friends(USER_DEFAULT)

df_users = pre_process_users_df(pd.concat([df_users, df_friends], ignore_index=True))
print(df_users.to_string(index=False))

# 3) pegar 20 appIds de jogos mais jogados por cada um dos 20 usuários registrados

# 4) pegar os detalhes dos jogos obtidos e reunir em um unico df (join com os appids de 3)) - somente trazer quando success === true

# 5) para cada imagem de cada jogo, criar um df para imagens com o appId do jogo

# 6) para cada video de cada jogo, criar um df para imagens com o appId do jogo

# 7) para cada categoria não repetida, criar um df de categorias

# 8) buscar conquistas de um jogo e adicionar a um df

# 9) buscar conquistas de um usuario e adicionar a um df (somente quando achieved === 1)

# 10) criar um novo df a partir de 9) com um join em 8) (mas manter o 8)), contendo steamid do usuario, appid do jogo e nane do jogo
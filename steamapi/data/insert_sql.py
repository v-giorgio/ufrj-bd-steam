import pandas as pd
from sqlalchemy import create_engine


# script para adicionar no banco:

# TODO: usar depois que tiver o banco criado e os dataframes completos

df = pd.DataFrame()

engine = create_engine('mysql+mysqlconnector://steam_user:123@localhost:3306/steam')

# Inserir os dados no banco de dados em uma tabela chamada 'apps'
df.to_sql('apps', con=engine, if_exists='append', index=False)
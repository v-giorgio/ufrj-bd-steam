from sqlite3 import IntegrityError
import pandas as pd
from sqlalchemy import create_engine

DATABASE_USER = 'root' # ou root
DATABASE_PASSWORD = '321' # se root: 321
DATABASE_HOST = 'localhost'
DATABASE_NAME = 'steam'

DATA_PATH = "./generated"

engine = create_engine(f'mysql+mysqlconnector://{DATABASE_USER}:{DATABASE_PASSWORD}@{DATABASE_HOST}/{DATABASE_NAME}')

csv_files = {
    "users.csv": ("Usuario", {}, []),
    "games.csv": ("Jogo", {}, ['capa', 'imagens', 'videos', 'categorias']),
    "categories.csv": ("Categoria", {}, []),
    "images.csv": ("Imagem", {"appId": "fk_Jogo_id"}, []),
    "videos.csv": ("Video", {"appId": "fk_Jogo_id"}, []),
    "achievements.csv": ("Conquista", {"appid": "fk_Jogo_id"}, []),
    "users_games.csv": ("JogoUsuario", {"steamid": "fk_Usuario_id", "appid": "fk_Jogo_id"}, []),
    "categories_games.csv": ("JogoCategoria", {"categoria_id": "fk_Categoria_id", "jogo_id": "fk_Jogo_id"}, []),
    "users_achievements.csv": ("UsuarioConquista", {"usuario_id": "fk_Usuario_id", "conquista_id": "fk_Conquista_id"}, []),
}

def insert_csv_to_sql(csv_files, data_path, engine):
    for csv_file, (table_name, column_mapping, unwanted_columns) in csv_files.items():
        df = pd.read_csv(f"{data_path}/{csv_file}")

        df.drop(columns=[col for col in unwanted_columns if col in df.columns], inplace=True, errors='ignore')

        if column_mapping:
            df.rename(columns=column_mapping, inplace=True)
        
        for index, row in df.iterrows():
            try:
                row.to_frame().T.to_sql(table_name, con=engine, if_exists='append', index=False)
                print(f"Row {index} from {csv_file} inserted into {table_name} table.")
            except IntegrityError as e:
                print(f"IntegrityError: {e} for row {index} in {csv_file}: {row.to_dict()}")


insert_csv_to_sql(csv_files=csv_files, data_path=DATA_PATH, engine=engine)
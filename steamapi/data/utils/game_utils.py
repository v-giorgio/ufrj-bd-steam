from datetime import datetime
from typing import Tuple
import pandas as pd
import requests

GAME_DETAILS = "http://store.steampowered.com/api/appdetails?appids="

def get_games_details(app_ids: list) -> pd.DataFrame:

    df_games = pd.DataFrame()
    
    for app_id in app_ids:
        response = requests.get(f"{GAME_DETAILS}{app_id}")
        game_data = response.json()[str(app_id)]
        
        if response.status_code == 200 and game_data['success']:
            game_data = game_data['data']

            df_game = pd.DataFrame([{
                'id': game_data['steam_appid'],
                'descricao': game_data.get('short_description', ""),
                'nome': game_data.get('name', ""),
                'spec_minima': game_data.get('pc_requirements', {}).get('minimum', "") if 'minimum' in game_data.get('pc_requirements', {}) else "",
                'spec_recomendada': game_data.get('pc_requirements', {}).get('recommended', "") if 'recommended' in game_data.get('pc_requirements', {}) else "",
                'genero': game_data['genres'][0]['description'] if game_data.get('genres') else "",
                'desenvolvedora': game_data['developers'][0] if game_data.get('developers') else "",
                'editora': game_data['publishers'][0] if game_data.get('publishers') else "",
                'classificacao_etaria': game_data.get('required_age', ""),
                'data_lancamento': parse_date(game_data.get('release_date', {}).get('date', "")),
                'gratuidade': game_data.get('is_free', False),
                'capa': game_data.get('header_image', ""),
                'imagens': [img['path_full'] for img in game_data.get('screenshots', [])],
                'videos': [
                    {
                        'url': video['webm'].get('max', ""),
                        'nome': video.get('name', "")
                    } for video in game_data.get('movies', [])
                ],
                'categorias': [
                    {
                        'id': cat['id'],
                        'nome': cat['description']
                    } for cat in game_data.get('categories', [])
                ]
            }]).fillna("")

            df_games = pd.concat([df_games, df_game], ignore_index=True)
            
    return df_games

def generate_images_videos_categories_dataframes(df_games: pd.DataFrame) -> Tuple[pd.DataFrame, pd.DataFrame, pd.DataFrame, pd.DataFrame]:
    images_data = []
    image_id = 1

    videos_data = []
    video_id = 1

    categories_data = []

    categories_games_data = []
    
    for index, row in df_games.iterrows():
        app_id = row['id']
        
        images_data.append({
            'id': image_id,
            'appId': app_id,
            'url': row['capa'],
            'capa': True
        })
        image_id += 1
        
        for img_url in row['imagens']:
            images_data.append({
                'id': image_id,
                'appId': app_id,
                'url': img_url,
                'capa': False
            })
            image_id += 1

        for video in row['videos']:
            videos_data.append({
                'id': video_id,
                'appId': app_id,
                'url': video['url'],
                'nome': video['nome']
            })
            video_id += 1

        for category in row['categorias']:
            categories_data.append({
                'id': category['id'],
                'nome': category['nome']
            })

            categories_games_data.append({
                'categoria_id': category['id'],
                'jogo_id': app_id
            })
            
    df_images = pd.DataFrame(images_data)

    df_videos = pd.DataFrame(videos_data)

    df_categories = pd.DataFrame(categories_data)
    df_categories = df_categories.drop_duplicates(subset=['id', 'nome']).reset_index(drop=True)

    df_categories_games = pd.DataFrame(categories_games_data)
    df_categories_games.drop_duplicates(subset=['categoria_id', 'jogo_id'], inplace=True)
    
    return df_images, df_videos, df_categories, df_categories_games

def parse_date(date_str: str) -> str:
    try:
        return datetime.strptime(date_str, "%d %b, %Y").strftime("%Y-%m-%d")
    except ValueError:
        try:
            return datetime.strptime(date_str, "%b %d, %Y").strftime("%Y-%m-%d")
        except ValueError:
            return ""
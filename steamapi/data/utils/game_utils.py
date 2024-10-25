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
                'data_lancamento': game_data['release_date']['date'] if 'release_date' in game_data else "",
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
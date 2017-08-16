# Android Challenge

Criar um aplicativo de filmes que consulte a API do TheMovieDatabase.

## Regras:
- Escolher algumas funcionalidades e implementar seguindo os critérios de [avaliação](#criterios_avaliacao)
- Nenhuma das funcionalidades dos desafios abaixo exigem autenticação do usuário. Podem ser acessados apenas usando alguma(s) chave(s) de aplicação dependendo da API.

## Navegação de filmes usando a [TheMovieDatabase API](https://www.themoviedb.org/documentation/api)

### <a name="filmes_cartaz" />1) Listagem de [filmes que estão em cartaz](https://developers.themoviedb.org/3/movies/get-now-playing)
- Use o parâmetro `language` com o valor pt (não há suporte para pt_BR) ou en.
- Campos sugeridos: Title (caso use a lingua `pt`), name, release date e vote average.
- Mostre uma imagem, usando os campos `backdrop_path` ou `poster_path`
    - Url para Imagens podem ser montadas de acordo com a [documentação](https://developers.themoviedb.org/3/configuration)
- A exibição pode ser em formato de lista ou grade
    - No caso de grade, exiba apenas o atributo `Title`.

### 2) Listagem de [filmes populares](https://developers.themoviedb.org/3/movies/get-popular-movies)
- Mesmas funcionalidades de [__filmes em cartaz__](#filmes_cartaz)

### 3) Listagem de [filmes mais bem avaliados](https://developers.themoviedb.org/3/movies/get-top-rated-movies)
- Mesmas funcionalidades de [__filmes em cartaz__](#filmes_cartaz)

### 4) [Detalhe do filme](https://developers.themoviedb.org/3/movies/get-movie-details)

### 5) [Trailers de filme](https://developers.themoviedb.org/3/movies/get-movie-videos)
- Youtube

### 6) [Busca de filmes](https://developers.themoviedb.org/3/getting-started/search-and-query-for-details)

*****

## Extras:

### Desenvolver no Android Studio.

### Uso de algum framework ou biblioteca para organização de arquitetura e/ou redução de código "boilerplate":
- Dagger2 ou Dagger
- Databind
- RxAndroid/RxJava
- ButterKnife
- Dart

### Gestão de dependências no projeto:
- Gradle
- Maven

### Mapeamento JSON ou XML -> POJO:
- GSON
- Jackson Mapper
- Moshi

### Framework para comunicação com API's externas:
- Volley
- Ion
- Wasp
- Retrofit

### Carregamento de imagens usando padrão de "lazyload":
- Picasso
- Fresco
- Glide
- Ion
- Volley

### Uso de alguma arquitetura com separação de responsabilidades:
- MVP
- MVVM

As bibliotecas citadas são apenas sugestões, sinta-se a vontade para usar outras.

*****

## <a name="criterios_avaliacao"/>Alguns pontos que serão ser avaliados

- Qualidade é melhor que quantidade.
- Usar Kotlin em vez de Java é um grande diferencial.
- Evite fazer requisições desnecessárias. Exemplos de tratamentos:
    - Cache de imagens.
    - Tente manter o estado durante rotações (caso seja permitido).
- Uso de níveis de SDK compatíveis com o mercado.
- Mantenha uma estrutura consistente de navegação, seja por abas ou menu deslizante, conforme o caso.
- Preocupação com compatibilidade e bom uso de biblioteca de suporte: Support Library v4, Appcompat v7, Design Library.
- Uso de componentes novos e/ou do Material Design: Toolbar, RecyclerView, AppBar, CoordinatorLayout, SnackBar, FloatActionButton e etc...
- Preocupação com organização de código/padronização.
- Uso de design patterns.
- Não é obrigatório tratamento para mudança de orientação, caso permita, será avaliado.
- Testes unitários ou instrumentados serão considerados extras.

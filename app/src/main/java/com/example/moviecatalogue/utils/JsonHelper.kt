package com.example.moviecatalogue.utils

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.example.moviecatalogue.data.source.remote.response.*
import org.json.JSONException
import java.io.InputStreamReader

class JsonHelper {

    private fun parsingFileToString(fileName: String): String {
        val content: String
        val reader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream(fileName))
        content = reader.readText()
        reader.close()
        return content
    }

    fun loadPlayingMovies(): ArrayList<ResultsItemMovies> {
        val list = ArrayList<ResultsItemMovies>()
        try {
            val dataMovies = parsingFileToString("response_movies.json")

            val parser: Parser = Parser.default()
            val stringBuilder: StringBuilder = StringBuilder(dataMovies)
            val json: JsonObject = parser.parse(stringBuilder) as JsonObject

            val resValue = json.array<JsonObject>("results")
            if (resValue != null) {
                for(i in 0 until resValue.size){
                    val overview = resValue[i].string("overview")
                    val originalLanguage = resValue[i].string("original_language")
                    val originalTitle = resValue[i].string("original_title")
                    val video = resValue[i].boolean("video")
                    val title = resValue[i].string("title")

                    val genreIds = resValue[i].array<Int>("genre_ids")
                    val posterPath = resValue[i].string("poster_path")
                    val backdropPath = resValue[i].string("backdrop_path")
                    val releaseDate = resValue[i].string("release_date")
                    val popularity = resValue[i].double("popularity")

                    val voteAverage = resValue[i].int("vote_average")?.toDouble()
                    val id = resValue[i].int("id")
                    val adult = resValue[i].boolean("adult")
                    val voteCount = resValue[i].int("vote_count")

                    val moviesResponse = ResultsItemMovies(
                            overview,
                            originalLanguage,
                            originalTitle,
                            video,
                            title,
                            genreIds,
                            posterPath,
                            backdropPath,
                            releaseDate,
                            popularity,
                            voteAverage,
                            id,
                            adult,
                            voteCount
                    )
                    list.add(moviesResponse)
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    fun loadPopularTv(): ArrayList<ResultsItemTv> {
        val list = ArrayList<ResultsItemTv>()
        try {
            val dataMovies = parsingFileToString("response_tv.json")

            val parser: Parser = Parser.default()
            val stringBuilder: StringBuilder = StringBuilder(dataMovies)
            val json: JsonObject = parser.parse(stringBuilder) as JsonObject

            val resValue = json.array<JsonObject>("results")
            if (resValue != null) {
                for(i in 0 until resValue.size){
                    val releaseDate = resValue[i].string("first_air_date")
                    val overview = resValue[i].string("overview")
                    val originalLanguage = resValue[i].string("original_language")
                    val genreIds = resValue[i].array<Int>("genre_ids")

                    val posterPath = resValue[i].string("poster_path")
                    val originCountry = resValue[i].array<String>("origin_country")
                    val backdropPath = resValue[i].string("backdrop_path")
                    val originalName = resValue[i].string("original_name")

                    val popularity = resValue[i].double("popularity")
                    val voteAverage = resValue[i].int("vote_average")?.toDouble()
                    val name = resValue[i].string("name")
                    val id = resValue[i].int("id")
                    val voteCount = resValue[i].int("vote_count")

                    val tvResponse = ResultsItemTv(
                            releaseDate,
                            overview,
                            originalLanguage,
                            genreIds,
                            posterPath,
                            originCountry,
                            backdropPath,
                            originalName,
                            popularity,
                            voteAverage,
                            name,
                            id,
                            voteCount
                    )
                    list.add(tvResponse)
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    fun loadDetailMovies(): MoviesDetailResponse {
        var data = MoviesDetailResponse()
        try {
            val dataMovies = parsingFileToString("response_detail_movies.json")

            val parser: Parser = Parser.default()
            val stringBuilder: StringBuilder = StringBuilder(dataMovies)
            val json: JsonObject = parser.parse(stringBuilder) as JsonObject

            val originalLanguage = json.string("original_language")
            val imdbId = json.string("imdb_id")
            val video = json.boolean("video")
            val title = json.string("title")
            val backdropPath = json.string("backdrop_path")

            val revenue = json.int("revenue")
            val dataGenres = json.array<JsonObject>("genres")
            val genres = dataGenres?.let { loadGenres(it) }
            val popularity = json.double("popularity")
            val dataProductionCountries = json.array<JsonObject>("production_countries")
            val prodCountries = dataProductionCountries?.let { loadProdCountries(it) }
            val id = json.int("id")

            val voteCount = json.int("vote_count")
            val budget = json.int("budget")
            val overview = json.string("overview")
            val originalTitle = json.string("original_title")
            val runTime = json.int("runtime")

            val posterPath = json.string("poster_path")
            val dataSpokenLanguages = json.array<JsonObject>("spoken_languages")
            val spokenLanguages = dataSpokenLanguages?.let { loadSpokenLang(it) }
            val dataProductionCompanies = json.array<JsonObject>("production_companies")
            val productionCompanies = dataProductionCompanies?.let { loadProdCompanies(it) }
            val releaseDate = json.string("release_date")
            val voteAverage = json.int("vote_average")?.toDouble()

            val belongsToCollection = json.string("belongs_to_collection")
            val tagLine = json.string("tagline")
            val adult = json.boolean("adult")
            val homepage = json.string("homepage")
            val status = json.string("status")

            val detailMoviesResponse = MoviesDetailResponse(
                originalLanguage,
                imdbId,
                video,
                title,
                backdropPath,
                revenue,
                genres,
                popularity,
                    prodCountries,
                id,
                voteCount,
                budget,
                overview,
                originalTitle,
                runTime,
                posterPath,
                spokenLanguages,
                productionCompanies,
                releaseDate,
                voteAverage,
                belongsToCollection,
                tagLine,
                adult,
                homepage,
                status
            )
            data = detailMoviesResponse
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return data
    }

    fun loadDetailTv(): TvDetailResponse {
        var data = TvDetailResponse()
        try {
            val dataMovies = parsingFileToString("response_detail_tv.json")

            val parser: Parser = Parser.default()
            val stringBuilder: StringBuilder = StringBuilder(dataMovies)
            val json: JsonObject = parser.parse(stringBuilder) as JsonObject

            val originalLanguage = json.string("original_language")
            val numberOfEpisodes = json.int("number_of_episodes")
            val dataNetworks = json.array<JsonObject>("networks")
            val networks = dataNetworks?.let { loadNetworks(it) }
            val type = json.string("type")
            val backdropPath = json.string("backdrop_path")

            val dataGenres = json.array<JsonObject>("genres")
            val genres = dataGenres?.let { loadGenres(it) }
            val popularity = json.double("popularity")
            val dataProductionCountries = json.array<JsonObject>("production_countries")
            val prodCountries = dataProductionCountries?.let { loadProdCountries(it) }
            val id = json.int("id")

            val numberOfSeasons = json.int("number_of_seasons")
            val voteCount = json.int("vote_count")
            val firstAirDate = json.string("first_air_date")
            val overview = json.string("overview")
            val dataSeasons = json.array<JsonObject>("seasons")
            val seasons = dataSeasons?.let { loadSeasons(it) }

            val languages = json.array<String>("languages")
            val dataCreatedBy = json.array<JsonObject>("created_by")
            val createdBy = dataCreatedBy?.let { loadCreatedBy(it) }
            val dataLastEpisodeToAir = json.obj("last_episode_to_air")
            val lastEpisodeToAir = dataLastEpisodeToAir?.let { loadLastEpisode(it) }
            val posterPath = json.string("poster_path")
            val originCountry = json.array<String>("origin_country")

            val dataSpokenLanguages = json.array<JsonObject>("spoken_languages")
            val spokenLanguages = dataSpokenLanguages?.let { loadSpokenLang(it) }
            val dataProductionCompanies = json.array<JsonObject>("production_companies")
            val productionCompanies = dataProductionCompanies?.let { loadProdCompanies(it) }
            val originalName = json.string("original_name")

            val voteAverage = json.int("vote_average")?.toDouble()
            val name = json.string("name")
            val tagLine = json.string("tagline")
            val episodeRunTime = json.array<Int>("episode_run_time")
            val dataNextEpisodeToAir = json.obj("next_episode_to_air")
            val nextEpisodeToAir = dataNextEpisodeToAir?.let { loadNextEpisode(it) }

            val inProduction = json.boolean("in_production")
            val lastAirDate = json.string("last_air_date")
            val homepage = json.string("homepage")
            val status = json.string("status")

            val detailTvResponse = TvDetailResponse(
                    originalLanguage,
                    numberOfEpisodes,
                    networks,
                    type,
                    backdropPath,
                    genres,
                    popularity,
                    prodCountries,
                    id,
                    numberOfSeasons,
                    voteCount,
                    firstAirDate,
                    overview,
                    seasons,
                    languages,
                    createdBy,
                    lastEpisodeToAir,
                    posterPath,
                    originCountry,
                    spokenLanguages,
                    productionCompanies,
                    originalName,
                    voteAverage,
                    name,
                    tagLine,
                    episodeRunTime,
                    nextEpisodeToAir,
                    inProduction,
                    lastAirDate,
                    homepage,
                    status
            )
            data = detailTvResponse
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return data
    }

    private fun loadGenres(genresItem: JsonArray<JsonObject>): List<GenresItem?> {
        val list = ArrayList<GenresItem>()
        for(i in 0 until genresItem.size){
            val name = genresItem[i].string("name")
            val id = genresItem[i].int("id")

            val response = GenresItem(
                    name,
                    id,
            )
            list.add(response)
        }
        return list
    }

    private fun loadProdCountries(prodCountries: JsonArray<JsonObject>): List<ProductionCountriesItem?> {
        val list = ArrayList<ProductionCountriesItem>()
        for(i in 0 until prodCountries.size){
            val iso31661 = prodCountries[i].string("iso_3166_1")
            val name = prodCountries[i].string("name")

            val response = ProductionCountriesItem(
                    iso31661,
                    name,
            )
            list.add(response)
        }
        return list
    }

    private fun loadProdCompanies(prodCompanies: JsonArray<JsonObject>): List<ProductionCompaniesItem?> {
        val list = ArrayList<ProductionCompaniesItem>()
        for(i in 0 until prodCompanies.size){
            val logo = prodCompanies[i].string("logo_path")
            val name = prodCompanies[i].string("name")
            val id = prodCompanies[i].int("id")
            val originCountry = prodCompanies[i].string("origin_country")

            val response = ProductionCompaniesItem(
                    logo,
                    name,
                    id,
                    originCountry
            )
            list.add(response)
        }
        return list
    }

    private fun loadSpokenLang(languageItem: JsonArray<JsonObject>): List<SpokenLanguagesItem?> {
        val list = ArrayList<SpokenLanguagesItem>()
        for(i in 0 until languageItem.size){
            val name = languageItem[i].string("name")
            val iso6391 = languageItem[i].string("iso_639_1")

            val response = SpokenLanguagesItem(
                    name,
                    iso6391
            )
            list.add(response)
        }
        return list
    }

    private fun loadNetworks(networksItem: JsonArray<JsonObject>): List<NetworksItem?> {
        val list = ArrayList<NetworksItem>()
        for(i in 0 until networksItem.size){
            val logoPath = networksItem[i].string("logo_path")
            val name = networksItem[i].string("name")
            val id = networksItem[i].int("id")
            val originCountry = networksItem[i].string("origin_country")

            val response = NetworksItem(
                    logoPath,
                    name,
                    id,
                    originCountry
            )
            list.add(response)
        }
        return list
    }

    private fun loadSeasons(seasonsItem: JsonArray<JsonObject>): List<SeasonsItem?> {
        val list = ArrayList<SeasonsItem>()
        for(i in 0 until seasonsItem.size){
            val airDate = seasonsItem[i].string("air_date")
            val overview = seasonsItem[i].string("overview")
            val episodeCount = seasonsItem[i].int("episode_count")
            val name = seasonsItem[i].string("name")
            val seasonNumber = seasonsItem[i].int("season_number")
            val id = seasonsItem[i].int("id")
            val posterPath = seasonsItem[i].string("poster_path")

            val response = SeasonsItem(
                    airDate,
                    overview,
                    episodeCount,
                    name,
                    seasonNumber,
                    id,
                    posterPath
            )
            list.add(response)
        }
        return list
    }

    private fun loadCreatedBy(createdItem: JsonArray<JsonObject>): List<CreatedByItem?> {
        val list = ArrayList<CreatedByItem>()
        for(i in 0 until createdItem.size){
            val gender = createdItem[i].int("gender")
            val creditId = createdItem[i].string("credit_id")
            val name = createdItem[i].string("name")
            val profilePath = createdItem[i].string("profile_path")
            val id = createdItem[i].int("id")

            val response = CreatedByItem(
                    gender,
                    creditId,
                    name,
                    profilePath,
                    id
            )
            list.add(response)
        }
        return list
    }

    private fun loadNextEpisode(episodeItem: JsonObject): NextEpisodeToAir {
        val productionCode = episodeItem.string("production_code")
        val airDate = episodeItem.string("air_date")
        val overview = episodeItem.string("overview")
        val episodeNumber = episodeItem.int("episode_number")
        val voteAverage = episodeItem.int("vote_average")?.toDouble()
        val name = episodeItem.string("name")
        val seasonNumber = episodeItem.int("season_number")
        val id = episodeItem.int("id")
        val stillPath = episodeItem.string("still_path")
        val voteCount = episodeItem.int("vote_count")

        return NextEpisodeToAir(
                productionCode,
                airDate,
                overview,
                episodeNumber,
                voteAverage,
                name,
                seasonNumber,
                id,
                stillPath,
                voteCount
        )
    }

    private fun loadLastEpisode(episodeItem: JsonObject): LastEpisodeToAir {
        val productionCode = episodeItem.string("production_code")
        val airDate = episodeItem.string("air_date")
        val overview = episodeItem.string("overview")
        val episodeNumber = episodeItem.int("episode_number")
        val voteAverage = episodeItem.int("vote_average")?.toDouble()
        val name = episodeItem.string("name")
        val seasonNumber = episodeItem.int("season_number")
        val id = episodeItem.int("id")
        val stillPath = episodeItem.string("still_path")
        val voteCount = episodeItem.int("vote_count")

        return LastEpisodeToAir(
                productionCode,
                airDate,
                overview,
                episodeNumber,
                voteAverage,
                name,
                seasonNumber,
                id,
                stillPath,
                voteCount
        )
    }

    fun loadImage(posterPath: String): String{
        val baseUri = "https://image.tmdb.org/t/p/"
        val size = "w500"
        return "${baseUri}${size}${posterPath}"
    }
}
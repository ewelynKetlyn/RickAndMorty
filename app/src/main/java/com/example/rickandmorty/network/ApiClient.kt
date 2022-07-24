package com.example.rickandmorty.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


object ApiClient {

    /**
     * https://rickandmortyapi.com/api/character/?page=1
     * O construtor do Retrofit precisará de uma URL base
     * Criação da variável base extraída do link
     */
    private val BASE_URL = "https://rickandmortyapi.com/api/"

    /**
     * Criação da variável para o construtor moshi, adicionando um conversor a ela
     */
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    /**
     * criação de uma instância retrofit by lazy, para que possa inicializar somente quando for
     * necessário passar as variáveis da base url e moshi criadas acima.
     */

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
    val apiService:ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}

/**
 * Criação da interface para definir como o retrofit conversa com o servidor unsando o método Get
 */
interface ApiService{
    /**
     * Criação da função fetchCharacter anotando com o @Get
     * passando o caminho do caractere como em nosso link da API acima para informar ao servidor
     * que nos envie esses caracteres
     */
    @GET("character")
    fun fetchCharacters(@Query("page")page:String): Call<CharacterResponse>
}
package com.example.remote

import com.example.remote.models.CharacterDto
import io.ktor.client.engine.*
import io.ktor.client.engine.mock.*
import io.ktor.http.*
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test

class VoldemortApiTest {

    private val VALID_RESPONSE = """
        [
	{
		"id": "9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8",
		"name": "Harry Potter",
		"alternate_names": [
			"The Boy Who Lived",
			"The Chosen One"
		],
		"species": "human",
		"gender": "male",
		"house": "Gryffindor",
		"dateOfBirth": "31-07-1980",
		"yearOfBirth": 1980,
		"wizard": true,
		"ancestry": "half-blood",
		"eyeColour": "green",
		"hairColour": "black",
		"wand": {
			"wood": "holly",
			"core": "phoenix feather",
			"length": 11
		},
		"patronus": "stag",
		"hogwartsStudent": true,
		"hogwartsStaff": false,
		"actor": "Daniel Radcliffe",
		"alternate_actors": [],
		"alive": true,
		"image": "https://ik.imagekit.io/hpapi/harry.jpg"
	}]
    """.trimIndent()

    private val INVALID_RESPONSE = """
	{
		"id": "9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8",
		"name": "Harry Potter",
		"alternate_names": [
			"The Boy Who Lived",
			"The Chosen One"
		],
		"species": "human",
		"gender": "male",
		"house": "Gryffindor",
		"dateOfBirth": "31-07-1980",
		"yearOfBirth": 1980,
		"hairColour": "black",
		"wand": {
			"wood": "holly",
			"core": "phoenix feather",
			"length": 11
		},
		"patronus": "stag",
		"hogwartsStudent": true,
		"hogwartsStaff": false,
		"actor": "Daniel Radcliffe",
		"alternate_actors": [],
		"alive": true,
		"image": "https://ik.imagekit.io/hpapi/harry.jpg"
	}]
    """.trimIndent()

    private fun createFakeClient(engine: HttpClientEngine) =
        VoldemortClient.getInstance(engine = engine)

    private fun createFakeEngine(content: String, status: HttpStatusCode) = MockEngine {
        respond(
            content = content,
            status = status,
            headers = headersOf(HttpHeaders.ContentType, "application/json")
        )
    }

    @Test
    fun `given VoldemortApi, when fetching Characters, should return a valid response`() =
        runBlocking {
            val fakeEngine =
                createFakeEngine(content = VALID_RESPONSE, status = HttpStatusCode.OK) // response
            val fakeClient = createFakeClient(engine = fakeEngine)
            val api = VoldemortApi.getInstance(client = fakeClient)
            val response = api.getCharacters() // request
            assert(response.isSuccessful)
            val data: List<CharacterDto> = Json.decodeFromString(VALID_RESPONSE)
            assertEquals(data, response.data)
        }

    @Test
    fun `given VoldemortApi, when fetching Characters, should return error if response is invalid`() =
        runBlocking {
            val fakeEngine =
                createFakeEngine(content = INVALID_RESPONSE, status = HttpStatusCode.OK)
            val fakeClient = createFakeClient(engine = fakeEngine)
            val api = VoldemortApi.getInstance(client = fakeClient)
            val response = api.getCharacters()
            assertEquals(false , response.isSuccessful)
            assertTrue(response.data == null)
        }

}
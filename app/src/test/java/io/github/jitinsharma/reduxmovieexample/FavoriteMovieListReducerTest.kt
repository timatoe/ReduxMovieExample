package io.github.jitinsharma.reduxmovieexample


/**
 * Created by jsharma on 21/02/18.
 */
// TODO: Fix tests
//@Suppress("FunctionName")
//class FavoriteListReducerTest {
//
//    @Test
//    fun checkFavoriteListReducer_withFavoritesAvailable() {
//        val action = DisplayFavoriteMovies(listOf(
//                MovieObject()
//        ))
//        val state = favoriteListReducer(action, null)
//        assertEquals(state.favorites?.size, 1)
//        assertFalse(state.displayNoFavoriteMessage)
//    }
//
//    @Test
//    fun checkFavoriteListReducer_withNoFavorites() {
//        val action = DisplayNoFavoriteMessage()
//        val state = favoriteListReducer(action, null)
//        assertNull(state.favorites)
//        assertTrue(state.displayNoFavoriteMessage)
//    }
//}
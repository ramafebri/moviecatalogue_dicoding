package com.example.moviecatalogue

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

class DrawableMatcher internal constructor(private val expectedId: Int) :
    TypeSafeMatcher<View?>(View::class.java) {
    private var resourceName: String? = null

    companion object {
        const val EMPTY = -1
        const val ANY = -2
    }

    override fun matchesSafely(item: View?): Boolean {
        if (item !is ImageView) {
            return false
        }
        val imageView: ImageView = item as ImageView
        if (expectedId == EMPTY) {
            return imageView.getDrawable() == null
        }
        if (expectedId == ANY) {
            return imageView.getDrawable() != null
        }
        val resources: Resources = item.getContext().getResources()
        val expectedDrawable: Drawable = resources.getDrawable(expectedId)
        resourceName = resources.getResourceEntryName(expectedId)
        val bitmap = getBitmap(imageView.getDrawable())
        val otherBitmap = getBitmap(expectedDrawable)
        return bitmap.sameAs(otherBitmap)
    }

    private fun getBitmap(drawable: Drawable): Bitmap {
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight())
        drawable.draw(canvas)
        return bitmap
    }

    override fun describeTo(description: Description) {
        description.appendText("with drawable from resource id: ")
        description.appendValue(expectedId)
        if (resourceName != null) {
            description.appendText("[")
            description.appendText(resourceName)
            description.appendText("]")
        }
    }
}
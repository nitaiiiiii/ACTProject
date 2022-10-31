package com.ci.act.util

import android.text.InputFilter
import android.text.Spanned
import java.util.regex.Pattern


class InputFilters {
    companion object {
        val emojiFilter = InputFilter { source, start, end, dest, dstart, dend ->
            for (index in start until end) {
                val type = Character.getType(source[index])
                if (type == Character.SURROGATE.toInt() || type == Character.NON_SPACING_MARK.toInt() || type == Character.OTHER_SYMBOL.toInt()) {
                    return@InputFilter ""
                }
            }
            null
        }

        private var alphabetPatterns: Pattern = Pattern.compile("[a-zA-Z ]+")

        val allowOnlyAlphabetsFilter = object : InputFilter {
            override fun filter(source: CharSequence, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int): CharSequence? {
                if (!alphabetPatterns.matcher(source).matches()) {
                    return ""
                }
                return null
            }
        }


        fun lengthFilter(length: Int = 255) = InputFilter.LengthFilter(length)

        fun decimalFilter(beforeDigits: Int, afterDigits: Int) = DecimalDigitsInputFilter(beforeDigits, afterDigits)

        fun decimal5Filter(beforeDigits: Int, afterDigits: Int) = DecimalDigits5InputFilter(beforeDigits, afterDigits)

        fun numberRangeFilter(min: Int, max: Int) = InputFilterMinMax(min, max)
    }

    class DecimalDigitsInputFilter(digitsBeforeZero: Int, digitsAfterZero: Int) :
        InputFilter {

        private var mPattern: Pattern =
            Pattern.compile("[0-9]{0," + (digitsBeforeZero - 1) + "}+((\\.[0-9]{0," + (digitsAfterZero - 1) + "})?)||(\\.)?")

        override fun filter(source: CharSequence, start: Int, end: Int, dest: Spanned, dstart: Int, dend: Int): CharSequence? {

            val matcher = mPattern.matcher(dest)
            return if (!matcher.matches()) "" else null
        }

    }

    class DecimalDigits5InputFilter(digitsBeforeZero: Int, digitsAfterZero: Int) :
        InputFilter {

        private var mPattern: Pattern =
            Pattern.compile("[0-9]{0," + (digitsBeforeZero - 1) + "}+((\\.[0-9]{0," + (digitsAfterZero - 1) + "})?)||(\\.)?")

        override fun filter(source: CharSequence, start: Int, end: Int, dest: Spanned, dstart: Int, dend: Int): CharSequence? {

            val matcher = mPattern.matcher(dest)
            return if (!matcher.matches()) {
                ""
            } else if (source.toString() == "." && !dest.startsWith("0") && !dest.startsWith("00")) {
                ""
            } else if (dest.endsWith(".")) {
                if (source.toString() == "5" && (dest.startsWith("0.") || dest.startsWith("00."))) {
                    null
                } else ""
            } else null
        }
    }

    class InputFilterMinMax(min: Int, max: Int) : InputFilter {

        private var min: Double = 0.0
        private var max: Double = 0.0

        init {
            this.min = min.toDouble()
            this.max = max.toDouble()
        }

        override fun filter(source: CharSequence, start: Int, end: Int, dest: Spanned, dstart: Int, dend: Int): CharSequence? {
            try {
                val input = (dest.toString() + source.toString()).toDouble()
                if (isInRange(min, max, input))
                    return null
            } catch (nfe: NumberFormatException) {
            }

            return ""
        }

        private fun isInRange(a: Double, b: Double, c: Double): Boolean {
            return if (b > a) c in a..b else c in b..a
        }

        val spaceFilter = object : InputFilter {
            override fun filter(
                source: CharSequence?,
                start: Int,
                end: Int,
                dest: Spanned?,
                dstart: Int,
                dend: Int
            ): CharSequence? {
                for (i in start until end) {
                    if (source?.get(i)?.let { Character.isWhitespace(it) }!!) {
                        return ""
                    }
                }
                return null
            }
        }
    }

}
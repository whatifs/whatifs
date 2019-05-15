using System;
using System.Collections.Generic;
using System.Text;

namespace AnagramLogic
{
    public static class Anagram
    {

        public static bool IsAnagram(string text1, string text2)
        {
            if (text1 == null) throw new ArgumentNullException(nameof(text1));
            if (text2 == null) throw new ArgumentNullException(nameof(text2));
            return false;
        }

    }
}

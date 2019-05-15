using System;
using System.Collections.Generic;
using AnagramLogic;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace AnagramTest
{
    [TestClass]
    public class AnagramTest
    {
        [TestMethod]
        public void IdenticalShortTextIsNotAnAnagram()
        {
            Assert.IsFalse(Anagram.IsAnagram("same", "same"));
        }

        [TestMethod]
        public void IdenticalLongerTextIsNotAnAnagram()
        {
            Assert.IsFalse(Anagram.IsAnagram("same but with spaces", "same but with spaces"));
        }

        [TestMethod]
        public void IdenticalTextWithSpacesIsNotAnAnagram()
        {
            Assert.IsFalse(Anagram.IsAnagram("same but with spaces", "same but with spaces"));
        }

        [TestMethod]
        private static void NullText1IsRejected()
        {
            try
            {
                Anagram.IsAnagram(null, "valid");
                Assert.Fail("expected exception not thrown");
            }
            catch (ArgumentNullException ex)
            {
                Assert.AreEqual("text1", ex.ParamName);
            }
        }

        [TestMethod]
        private static void NullText2IsRejected()
        {
            try
            {
                Anagram.IsAnagram("valid", null);
                Assert.Fail("expected exception not thrown");
            }
            catch (ArgumentNullException ex)
            {
                Assert.AreEqual("text1", ex.ParamName);
            }
        }

        [TestMethod]
        private static void TwoNullTextsAreRejected()
        {
            try
            {
                Anagram.IsAnagram(null, null);
                Assert.Fail("expected exception not thrown");
            }
            catch (ArgumentNullException ex)
            {
                Assert.AreEqual("text1", ex.ParamName);
            }
        }
    }
}
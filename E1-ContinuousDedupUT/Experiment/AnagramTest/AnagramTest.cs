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
        public void IdenticalTextIsNotAnAnagram()
        {
            foreach (var example in new[] {"same","samebutlonger","same but with spaces"})
            {
                AssertIdenticalTextIsNotAnAnagram(example);
            }
        }

        [TestMethod]
        public void NullTextIsRejected()
        {
            var invalidNullExample = new [] { null, "valid" };
            foreach (var example in new[] {
                invalidNullExample,
                ReverseArray(invalidNullExample),
                TwoOf(null)
            })
            {
                AssertNullTextIsRejected(example);
            }
        }

        private static void AssertNullTextIsRejected(string[] example)
        {
            var text1 = example[0];
            var text2 = example[1];
            try
            {
                Anagram.IsAnagram(text1, text2);
                Assert.Fail("expected exception not thrown");
            }
            catch (ArgumentNullException ex)
            {
                Assert.AreEqual("text" + (text1 == null ? "1" : "2"), ex.ParamName);
            }
        }

        string[] ReverseArray(string[] array)
        {
            var reversed = new List<string>(array);
            reversed.Reverse();
            return reversed.ToArray();
        }

        string[] TwoOf(string value)
        {
            return new[] { value, value };
        }

        public void AssertIdenticalTextIsNotAnAnagram(string same)
        {
            Assert.IsFalse(Anagram.IsAnagram(same, same));
        }
    }
}

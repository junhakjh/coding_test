class Solution:
    def fullJustify(self, words: List[str], maxWidth: int) -> List[str]:
        answer = []
        candidate, length = [words[0]], len(words[0])
        for word in words[1:]:
            if len(word) + (length + 1) > maxWidth:
                if len(candidate) == 1:
                    string = candidate[0]
                    for _ in range(maxWidth - len(candidate[0])):
                        string += ' '
                else:
                    all_plus = (maxWidth - length) // (len(candidate) - 1)
                    rest = (maxWidth - length) % (len(candidate) - 1)
                    string = ''
                    for i, w in enumerate(candidate):
                        string += w
                        for _ in range(all_plus):
                            if i != len(candidate) - 1:
                                string += ' '
                        if i != len(candidate) - 1:
                            string += ' '
                        if rest:
                            string += ' '
                            rest -= 1
                answer.append(string)

                candidate = [word]
                length = len(word)

            else:
                candidate.append(word)
                length += len(word) + 1

        string = ''
        for word in candidate:
            string += word + ' '
        for _ in range(maxWidth - len(string)):
            string += ' '
        answer.append(string[:maxWidth])

        return answer

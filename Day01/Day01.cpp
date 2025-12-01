#include "Utils.h"
#include <iostream>
#include <string>
#include <vector>

constexpr int DIAL_START_POSITION = 50;
constexpr int DIAL_SIZE = 100;

int resolvePart1(const std::vector<std::pair<std::string, int>> &input);
int resolvePart2(const std::vector<std::pair<std::string, int>> &input);
int mod(int a, int b);

int main() {
    std::vector<std::string> input = readInput("Day01.txt");

    std::vector<std::pair<std::string, int>> parsedInput;
    parsedInput.reserve(input.size());

    for (const std::string &entry : input) {
        std::string directionLetter = entry.substr(0, 1);
        int number = std::stoi(entry.substr(1));
        parsedInput.emplace_back(directionLetter, number);
    }

    std::cout << "Part 1: " << resolvePart1(parsedInput) << std::endl;
    std::cout << "Part 2: " << resolvePart2(parsedInput) << std::endl;

    return 0;
}

int resolvePart1(const std::vector<std::pair<std::string, int>> &input) {
    int currentDialPosition = DIAL_START_POSITION;
    int result = 0;

    for (std::pair<std::string, int> entry : input) {
        if (entry.first == "L") {
            currentDialPosition = (currentDialPosition - entry.second) % DIAL_SIZE;
        } else {
            currentDialPosition = (currentDialPosition + entry.second) % DIAL_SIZE;
        }

        if (currentDialPosition == 0) {
            ++result;
        }
    }

    return result;
}

int resolvePart2(const std::vector<std::pair<std::string, int>> &input) {
    int currentDialPosition = DIAL_START_POSITION;
    int result = 0;

    for (std::pair<std::string, int> entry : input) {
        if (entry.first == "L") {
            if (currentDialPosition == 0) {
                result += ((entry.second + DIAL_SIZE - currentDialPosition) / DIAL_SIZE) - 1;
            } else {
                result += (entry.second + DIAL_SIZE - currentDialPosition) / DIAL_SIZE;
            }
            currentDialPosition = mod(currentDialPosition - entry.second, DIAL_SIZE);
        } else {
            result += (entry.second + currentDialPosition) / DIAL_SIZE;

            currentDialPosition = (currentDialPosition + entry.second) % DIAL_SIZE;
        }
    }

    return result;
}

int mod(int a, int b) {
    int r = a % b;
    return (r < 0) ? r + b : r;
}

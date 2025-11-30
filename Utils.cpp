#include "Utils.h"
#include <filesystem>
#include <fstream>
#include <iostream>

std::vector<std::string> readInput(const std::string &fileName)
{
    std::ifstream inputFile(fileName);

    if (!inputFile.is_open())
    {
        std::cerr << std::filesystem::current_path() << std::endl;
        throw std::runtime_error("Cannot open file: " + fileName);
    }

    std::vector<std::string> lines;
    std::string line;

    while (std::getline(inputFile, line))
    {
        lines.push_back(line);
    }

    inputFile.close();

    return lines;
}

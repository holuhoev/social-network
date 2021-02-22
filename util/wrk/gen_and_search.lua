math.randomseed(os.time())
request = function()
    local alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' }
    local lastNamePrefix = alphabet[math.random(26)] .. alphabet[math.random(26)] .. alphabet[math.random(26)]
    local firstNamePrefix = alphabet[math.random(26)] .. alphabet[math.random(26)]
    local url_path = "/users/search?lastName=" .. lastNamePrefix  .. "&firstName=" .. firstNamePrefix
    return wrk.format("GET", url_path)
end
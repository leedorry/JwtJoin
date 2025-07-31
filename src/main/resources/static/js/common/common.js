
async function fetchCommon(url, method, data) {
    const response = await fetch(url,
        {
            method: method? method: "GET",
            body: data? JSON.stringify(data) : null
        }
    );
    return await response.json();
}
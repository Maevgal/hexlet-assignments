@import gg.jte.Content
@param Content content
@param Content footer = null

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Мой первый сайт</title>
</head>
<body>
<div class="mx-auto p-4 py-md-5">
    ${content}
</div>

@if (footer != null)
    <footer class="text-center text-lg-start bg-light text-muted">
        <div class="text-center p-4" style="background-color: rgba(0, 0, 0, 0.05);">
            ${footer}
        </div>
    </footer>
@endif
</body>
</html>



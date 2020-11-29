package views
package html.puzzle

import controllers.routes
import play.api.i18n.Lang

import lila.api.Context
import lila.app.templating.Environment._
import lila.app.ui.ScalatagsTemplate._
import lila.puzzle.PuzzleTheme

object theme {

  def list(themes: List[PuzzleTheme.WithCount])(implicit ctx: Context) =
    views.html.base.layout(
      title = "Puzzle themes",
      moreCss = cssTag("puzzle.page")
    )(
      main(cls := "page-small box")(
        h1("Puzzle themes"),
        div(cls := "puzzle-themes")(
          themes map { pt =>
            val url =
              if (pt.theme == PuzzleTheme.any) routes.Puzzle.home()
              else routes.Puzzle.show(pt.theme.key.value)
            a(cls := "puzzle-themes__link", href := url)(
              span(
                strong(
                  pt.theme.name(),
                  em(pt.count.localize)
                ),
                span(pt.theme.description())
              )
            )
          }
        )
      )
    )
}